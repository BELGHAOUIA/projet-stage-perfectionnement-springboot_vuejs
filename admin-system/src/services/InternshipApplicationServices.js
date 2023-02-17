import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8082",
  withCredentials: false,
  headers: {
    /*Accept: "application/json",
    "Content-Type": "application/json",*/
    "Access-Control-Allow-Origin": "*",
  },
});

export default {
  
  async viewAll() {
    return apiClient.get("/viewAllInternship").then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },

  async AcceptOrDecline(id,a) {
    return apiClient.put(`/internshipAcceptOrDecline?id=${id}&response=${a}`).then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },

  async undefined() {
    return apiClient.get('/internshipApplicationUndefined').then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  async accepted() {
    return apiClient.get('/internshipApplicationAccepted').then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  async refused() {
    return apiClient.get('/internshipApplicationRefused').then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
};
