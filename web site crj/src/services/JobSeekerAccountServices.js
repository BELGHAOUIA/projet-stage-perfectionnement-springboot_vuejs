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
  async add(a) {
    return apiClient.post(`/addJobSeekerAccount`, a).then((response) => {
     //console.log(response.data);
     return response.data;
    });
   },

   async putFile(id, p) {
    return apiClient.put(`/putCV?id=${id}`, p).then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },

  async count() {
    return apiClient.get(`/jobSeekerAccountCount`).then((response) => {
     //console.log(response.data);
     return response.data;
    });
   },

   async existsById(id) {
    return apiClient.get(`/jobSeekerExistsById?id=${id}`).then((response) => {
      //console.log("exists by id" + response.data);
      return response.data;
    });
  },

   async getById(id) {
    return apiClient.get(`/getJobSeekerAccountById?id=${id}`).then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },

   async existsByIdAndPassword(id, password) {
    return apiClient.get(`/jobSeekerExistsByIdAndPass?id=${id}&password=${password}`).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
};
