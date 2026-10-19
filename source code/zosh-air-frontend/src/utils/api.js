import axios from 'axios';

// const localHost="http://localhost:5000"
// const vpsIP="http://194.164.148.89:5000"
// While developing (pnpm dev) talk to the local gateway; a built site calls the server it was loaded from.
const deployed = import.meta.env.DEV ? "http://localhost:5000" : ""

const api = axios.create({
  baseURL: deployed,
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;

