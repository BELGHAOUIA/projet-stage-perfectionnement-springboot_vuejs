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
    return apiClient.post("/addJobSeekerOpenJob", a).then((response) => {
     // console.log(response.data);
      return response.data;
    });
  },

  async getApplication(id) {
    return apiClient.get(`/getAllJobApplicationById?id=${id}`).then((response) => {
       console.log(response.data);
      return response.data;
    });
  },
};
