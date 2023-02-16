import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8082",
  withCredentials: false,
  headers: {
   /* Accept: "application/json",
    "Content-Type": "application/json",*/
    "Access-Control-Allow-Origin": "*",
  },
});

export default {

   async viewAll() {
     return apiClient.get("/viewAllNewsTeller").then((response) => {
     // console.log(response.data);
      return response.data;
    });
  },

  async getFile(id) {
    return apiClient.get(`/getFile?id=${id}`, { responseType: 'blob' }).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },

};
