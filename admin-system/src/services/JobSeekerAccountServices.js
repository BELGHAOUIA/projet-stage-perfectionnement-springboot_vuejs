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
    return apiClient.get(`/jobSeekerAccountCount`).then((response) => {
     //console.log(response.data);
     return response.data;
    });
   },

  async getFile(id) {
    return apiClient.get(`/getCV?id=${id}`, { responseType: 'arraybuffer' }).then((response) => {
     //console.log(response.data);
     return response.data;
    });
   },


};
