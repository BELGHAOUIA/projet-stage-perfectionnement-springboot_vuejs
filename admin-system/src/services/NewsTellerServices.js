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
    return apiClient.post("/addNewsTeller", a).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  
  async update(id, a) {
    return apiClient.put(`/updateNewsTeller?id=${id}`, a).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  
   async viewAll() {
     return apiClient.get("/viewAllNewsTeller").then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },

  async delete(id) {
    return apiClient.delete(`/deleteNewsTeller?id=${id}`).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },

  async putFile(id, p) {
    return apiClient.put(`/putFile?id=${id}`, p).then((response) => {
      // console.log(response.data);
      return response.data;
     });
  },

  async getFile(id) {
    return apiClient.get(`/getFile?id=${id}`, { responseType: 'arraybuffer' }).then((response) => {
      //console.log(response.data);
      return response.data;
    });
  },
  
};
