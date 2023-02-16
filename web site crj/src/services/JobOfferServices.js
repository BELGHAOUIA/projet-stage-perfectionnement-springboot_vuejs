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
     return apiClient.get("/viewAllJobOffer").then((response) => {
     // console.log(response.data);
      return response.data;
    });
  },
  
  async delete(id) {
    return apiClient.delete(`/deleteJobOffer?id=${id}`).then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },

  async getById(id) {
     return apiClient.get(`/getJobOfferById?id=${id}`).then((response) => {
     // console.log(response.data);
      return response.data;
    });
  },

};
