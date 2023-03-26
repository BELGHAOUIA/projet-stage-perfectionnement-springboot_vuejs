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
    return apiClient.post(`/addInternAccount`, a).then((response) => {
     //console.log(response.data);
     return response.data;
    });
   },

   async existsById(id) {
    return apiClient.get(`/traineeExistsById?id=${id}`).then((response) => {
      //console.log("exists by id" + response.data);
      return response.data;
    });
  },

   async getById(id) {
    return apiClient.get(`/getInternAccountById/${id}/`).then((response) => {
      //console.log(response.data);
      return response.data;
     });
   },

   async existsByIdAndPassword(id, password) {
    return apiClient.get(`/traineeExistsByIdAndPass?id=${id}&password=${password}`).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },

  async getFile(id) {
    return apiClient.get(`/getAcceptedInternship?id=${id}`, { responseType: 'blob' }).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  
  async download(id) {
    return apiClient.get(`/getAcceptedInternship?id=${id}`,  { responseType: 'arraybuffer' }).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  }
,
};
