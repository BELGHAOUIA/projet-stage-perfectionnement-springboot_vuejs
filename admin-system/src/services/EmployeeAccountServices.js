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
    return apiClient.post("/addEmployeeAccount", a).then((response) => {
     // console.log(response.data);
      return response.data;
    });
  },
  
  async update(id, a) {
    return apiClient.put(`/updateEmployeeAccount?id=${id}`, a).then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },
  
     async viewAll() {
     return apiClient.get("/viewAllEmployeeAccount").then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },
  
  async delete(id) {
    return apiClient.delete(`/deleteEmployeeAccount?id=${id}`).then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },
  
  async putAvatar(id, p) {
    return apiClient.put(`/putAvatar?id=${id}`, p).then((response) => {
      // console.log(response.data);
      return response.data;
    });
  },

  async getFile(id) {
    return apiClient.get(`/getAvatar?id=${id}`, { responseType: 'arraybuffer' }).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  }
,
  
  async existsById(id) {
    return apiClient.get(`/existsById?id=${id}`).then((response) => {
      //console.log("exists by id" + response.data);
      return response.data;
    });
  },

  async existsByIdAndPassword(id, password) {
    return apiClient.get(`/employeeExistsByIdAndPass?id=${id}&password=${password}`).then((response) => {
      console.log(response.data);
      return response.data;
    });
  },

  async count() {
    return apiClient.get(`/employeeAccountCount`).then((response) => {
     //console.log(response.data);
     return response.data;
    });
   },

};
