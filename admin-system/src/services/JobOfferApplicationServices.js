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
    return apiClient.get("/viewAllJobSeekerOpenJob").then((response) => {
       console.log(response.data);
      return response.data;
    });
  },

  async AcceptOrDecline(id,a) {
    return apiClient.put(`/jobApplicationAcceptOrDecline?response=${a}`, id).then((response) => {
       console.log(response.data);
      return response.data;
    });
  },

  async undefined() {
    return apiClient.get('/jobOfferApplicationUndefined').then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  async accepted() {
    return apiClient.get('/jobOfferApplicationAccepted').then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  async refused() {
    return apiClient.get('/jobOfferApplicationRefused').then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },


};
