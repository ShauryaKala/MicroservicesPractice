#!/usr/bin/env bash
# One-time setup on a fresh Ubuntu 24.04 EC2 server. Run from anywhere:
#   bash ~/MicroservicesPractice/deploy/aws-setup.sh
# It installs Docker + Java, builds every service and the website, starts the site on port 80,
# and makes it start again automatically each time you turn the server back on.
set -euo pipefail
REPO="$(cd "$(dirname "$0")/.." && pwd)"
APP="$REPO/source code/microservices"
COMPOSE="$APP/docker-compose/docker-compose.aws.yml"

if [ "${1:-}" != "build" ]; then
  echo "==> Adding 4 GB of swap (extra breathing room for an 8 GB server)"
  if ! sudo swapon --show | grep -q /swapfile; then
    sudo fallocate -l 4G /swapfile && sudo chmod 600 /swapfile
    sudo mkswap /swapfile && sudo swapon /swapfile
    echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab >/dev/null
  fi
  echo "==> Installing Docker and Java 21"
  command -v docker >/dev/null || curl -fsSL https://get.docker.com | sudo sh
  sudo apt-get update -y && sudo apt-get install -y openjdk-21-jdk-headless
  sudo usermod -aG docker "$USER"
  exec sg docker -c "bash '$0' build"
fi

echo "==> Building the Java services (takes ~10 minutes the first time)"
cd "$APP"
chmod +x common-lib/mvnw
./common-lib/mvnw -B -q clean install -DskipTests
for app in cloud/config-server cloud/service-registry cloud/api-gateway \
           services/user-service services/airline-core-service services/location-service \
           services/flight-ops-service services/seat-service services/pricing-service \
           services/ancillary-service services/booking-service services/payment-service; do
  echo "    image: $app"
  ./common-lib/mvnw -B -q -f "$app/pom.xml" compile jib:dockerBuild
done

echo "==> Building the website"
cd "$REPO/source code/zosh-air-frontend"
docker run --rm -v "$PWD":/app -w /app node:22 sh -c "npm i -g pnpm >/dev/null && pnpm install && pnpm build"

echo "==> Starting everything"
docker compose -f "$COMPOSE" up -d

echo "==> Auto-start on boot"
( crontab -l 2>/dev/null | grep -v docker-compose.aws.yml
  echo "@reboot sleep 30 && docker compose -f \"$COMPOSE\" up -d" ) | crontab -

IP=$(curl -s https://checkip.amazonaws.com || echo YOUR-SERVER-IP)
echo
echo "Done. Give it 3-4 minutes to warm up, then open: http://$IP"
