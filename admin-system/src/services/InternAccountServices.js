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
  async count() {
    return apiClient.get(`/internAccountCount`).then((response) => {
     //console.log(response.data);
     return response.data;
    });
   },

  async acceptInternship(id) {
    return apiClient.put(`/acceptInternship?id=${id}`).then((response) => {
     //console.log(response.data);
     return response.data;
    });
   },


};
