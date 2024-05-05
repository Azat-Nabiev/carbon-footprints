import { getSavedId, getAurhorizationHeaderValue } from "./storage";
import axios from "axios";

const instance = axios.create({
  baseURL: "/api",
  timeout: 3000,
});

instance.interceptors.request.use((cfg) => {
  try {
    const token = getAurhorizationHeaderValue();
    cfg.headers.Authorization = token;
  } catch {
    console.error(`No token in interceptor ${cfg.url}`);
  }
  return cfg;
});

instance.interceptors.response.use((res) => res.data);

export function login(obj) {
  return instance.post("/auth/authenticate", obj);
}

export function register(obj) {
  return instance.post("/auth/register", obj);
}

export function getUserProfileInfo() {
  const id = getSavedId();
  return instance.get(`/user/${id}`);
}

export function getUserAddresses() {
  const id = getSavedId();
  return instance.get(`/address/full/${id}`);
}

export function addAddress(obj) {
  const id = getSavedId();
  return instance.post(`/address`, obj, {
    headers: {
      USER_ID: id,
    },
  });
}

export function changeAddress(obj) {
  const id = getSavedId();
  return instance.put(`/address/${obj.id}`, obj, {
    headers: {
      USER_ID: id,
    },
  });
}

export function deleteAddress(obj) {
  return instance.delete(`/address/${obj.id}`, obj);
}

export function getRating() {
  const id = getSavedId();
  return instance.get(`/user/rate/${id}`);
}

export function getUserConsumedResources() {
  const id = getSavedId();
  return instance.get(`/carbon/type/${id}`);
}

export function getUserCarbonAmount() {
  const id = getSavedId();
  return instance.get(`/carbon/amount/${id}`);
}

export function getConsumablesType() {
  return instance.get(`/carbon/coef`);
}

export function getShortAddresses() {
  const id = getSavedId();
  return instance.get(`/address/compact/${id}`);
}

export function getAddressReport() {
  const id = getSavedId();
  return axios({
    url: `/api/address/report/${id}`,
    method: "GET",
    responseType: "blob",
    headers: {
      Authorization: getAurhorizationHeaderValue(),
    },
  }).then((response) => {
    const href = window.URL.createObjectURL(response.data);
    const anchorElement = document.createElement("a");
    anchorElement.href = href;
    anchorElement.download = "result.xlsx";
    document.body.appendChild(anchorElement);
    anchorElement.click();
    document.body.removeChild(anchorElement);
    window.URL.revokeObjectURL(href);
  });
}
