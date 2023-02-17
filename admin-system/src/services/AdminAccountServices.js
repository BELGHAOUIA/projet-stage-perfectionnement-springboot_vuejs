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
   async add(a) {
     return apiClient.post("/addAdminAccount", a).then((response) => {
       console.log(response.data);
       return response.data;
     });
   },

  async update(id, a) {
    return apiClient.put(`/updateAdminAccount?id=${id}`, a).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  
  // async viewAll(id) {
  //   return apiClient.get(`/viewAdminsAccounts?id=${id}`).then((response) => {
  //     //console.log(response.data);
  //     return response.data;
  //   });
  // },

  // async delete(id) {
  //   return apiClient.delete(`/deleteAdminAccount?id=${id}`).then((response) => {
  //     //console.log(response.data);
  //     return response.data;
  //   });
  // },

  async existsByIdAndPassword(id, a) {
    return apiClient.get(`/verifyAdminAccountExists?id=${id}&password=${a}`).then((response) => {
      console.log(response.data);
      return response.data;
    });
  },

  // async getById(id) { 
  //   return apiClient.get(`/getAccountById?id=${id}`).then((response) => {
  //     //console.log(response.data);
  //     return response.data;
  //   });
  // },

   async count() {
    return apiClient.get('/adminAccountCount').then((response) => {
      //console.log(response.data);
      return response.data;
    });
    
  }
};
