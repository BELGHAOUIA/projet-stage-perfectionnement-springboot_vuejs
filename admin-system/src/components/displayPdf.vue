<template>
    <div>
        <v-form>
        <input type="file" ref="fileInput" @change="uploadFile"/>
        </v-form>
      <v-btn @click="downloadFile()">Download File</v-btn>
    </div>
  </template>
  
  <script>
  import axios from "axios"
  import EmployeeAccountServices from '../services/EmployeeAccountServices';


  export default {
    data () {
      return {
      }
    },
    methods: {
      async downloadFile() {
        //await axios.put("http://localhost:8082/acceptInternship/"+ "amal");
        await axios.get('http://localhost:8082/getInternAccountById/' + "new")
        .then(response => {
               const pdf = response.data;
               const pdf1 = pdf.file;
               this.file = new Blob(pdf1, { type: 'application/pdf' });
               this.fileUrl = URL.createObjectURL(this.file);
          })
          .catch(error => {
              console.log(error);
          });/*
         await axios.get('http://localhost:8082/api/pdf/' + "new", { responseType: 'arraybuffer' })
          .then(response => {
               this.file = new Blob([response.data], { type: 'application/pdf' });
               this.fileUrl = URL.createObjectURL(this.file);
              const anchor = document.createElement('a');
              anchor.href = this.fileUrl;
              anchor.download = 'fileName.pdf';
              document.body.appendChild(anchor);
              anchor.click();
              anchor.addEventListener("click", function() {
                  URL.revokeObjectURL(this.fileUrl);
              });
          })
          .catch(error => {
              console.log(error);
          });*/
      },
      async uploadFile() {

    const employee = {
          email: "fuck",
          firstName: "amal",
          lastName: "sdcdhhj",
          password: "jdjkdsj",
          phoneNumber: "15415241",
        }
        await EmployeeAccountServices.add(employee)

        let input = this.$refs.fileInput
        let file = input.files[0]
        let formData = new FormData()
        formData.append('file', file)

        EmployeeAccountServices.putAvatar("fuck", formData)
       // axios.put("http://localhost:8082/putAvatar?id=amal", formData);
      /* return axios.get(${process.env.VUE_APP_API_INTRANET}/pdf, {
  responseType: "blob"
}).then(response => {
  console.log("Success", response);
  const blob = new Blob([response.data]);
  const objectUrl = URL.createObjectURL(blob);
  this.pdfsrc = objectUrl;
})*/
    }
    },
    mounted () {
    }
  }
  </script>
  