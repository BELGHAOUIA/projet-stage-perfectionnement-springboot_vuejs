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
  
  async getById(id) {
    return apiClient.get(`/getEmployeeAccountById?id=${id}`).then((response) => {
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
    return apiClient.get(`/getAvatar?id=${id}`, { responseType: 'blob' }).then((response) => {
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

};
