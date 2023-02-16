<template>
    <div>
    <div v-for="file in p" :key="file.email">
      <pdf :src="file.file"></pdf>
    </div>
    </div>
  </template>
  
  <script>
  import intern from "../services/InternAccountServices"
  import pdf from 'vue-pdf'
  import axios from 'axios'


  export default {
  components: {
    pdf
  },
    data () {
      return {
        pdfsrc: null,
        p: []
      }
    },
    methods: {
       async downloadFile() {
        /* axios.get('http://localhost:8082/api/pdf/' + "new", { responseType: 'blob' })
         .then(response => {
           console.log("Success", response);
           const blob = new Blob([response.data]);
           const objectUrl = URL.createObjectURL(blob);
           this.pdfsrc = objectUrl;
           objectUrl.revokeObjectURL
          })
          
          const intern = {
            email: "amal",
            firstName: "fghjkl",
            lastName: "hgfd",
            password: "742fghjk",
            phoneNumber: "12345678"
          }
          await axios.post("http://localhost:8082/addInternAccount/",{
            email: "mmm",
            firstName: "fghjkl",
            lastName: "hgfd",
            password: "742fghjk",
            phoneNumber: "12345678"
          });*/
          await axios.get('http://localhost:8082/viewAllInternAccount/')
          .then(response => {
            this.p = response.data;
            console.log(this.p);
          })
          .catch(error => {
            console.log(error);
          });
          
          for(let i = 0; i < this.p.length; i++) {
            await axios.get('http://localhost:8082/api/pdf/' + this.p[i].email, { responseType: 'blob' })
            .then(response => {
              console.log("Success", response.data);
              const blob = new Blob([response.data]);
              const objectUrl = URL.createObjectURL(blob);
              this.pdfsrc = objectUrl;
              this.p[i].file = this.pdfsrc;
              objectUrl.revokeObjectURL
            })
          }
          
          
        await intern.getAccount("new")
        .then(response => {
          console.log(response.data);
          console.log("Success", response);
          const blob = new Blob([response.data].file);
          const objectUrl = URL.createObjectURL(blob);
          this.pdfsrc = objectUrl;
          objectUrl.revokeObjectURL
        })
        
          

        },
    },
    created () {
      this.downloadFile();
    }
  }
  </script>
  