<template>
    <div>
      <pdf :src="pdfsrc"></pdf>
    </div>
  </template>
  
  <script>
  import axios from "axios"
  import pdf from 'vue-pdf'


  export default {
  components: {
    pdf
  },
    data () {
      return {
        pdfsrc: null
      }
    },
    methods: {
       downloadFile() {
/*
        axios.get('http://localhost:8082/getInternAccountById/' + "new", { responseType: 'blob' })
         .then(response => {
         console.log("Success", response);
         const blob = new Blob([response.data]);
        const objectUrl = URL.createObjectURL(blob);
       this.pdfsrc = objectUrl;
       //objectUrl.revokeObjectURL
       })*/
        //await axios.put("http://localhost:8082/acceptInternship/"+ "amal");
         axios.get('http://localhost:8082/getInternAccountById/' + "new")
        .then(response => {
               const pdf = response.data;
               const pdf1 = pdf.file;
               this.file = new Blob(pdf1, { type: 'application/pdf' });
               this.fileUrl = URL.createObjectURL(this.file);
          })
          .catch(error => {
              console.log(error);
          });

          /*
          axios.get('http://localhost:8082/getInternAccountById/' + "new")
         .then(response => {
          console.log(response.data);
         console.log("Success", response);
         const pdf = response.data;
         this.pdfsrc = pdf
         const blob = new Blob([response.data].accepted_internship_pdf_file);
        const objectUrl = URL.createObjectURL(blob);
       this.pdfsrc = objectUrl;
       //objectUrl.revokeObjectURL
      })
      */
      },
    },
    created () {
      this.downloadFile();
    }
  }
  </script>
  