<template>
    <div>
      <v-subheader class="py-0 d-flex justify-space-between rounded-lg">
              <h3>Creating A News Teller</h3>
          </v-subheader>
          <v-divider></v-divider>
          <br>
          <br>
    <v-form 
      class="form text-center mr-16 ml-16"
      ref="form"
      v-model="valid"
      lazy-validation
    >

        <v-text-field
        :rules="[v => !!v || 'Title is required']"
          v-model="title"
          counter
          label="Enter The Title"
          required
          outlined
          prepend-icon="mdi-chevron-triple-right"
        ></v-text-field>

        <v-file-input
    v-model="file"
    :rules="[v => !!v || 'File is required']"
    color="accent-4"
    counter
    label="File input"
    placeholder="Select your file"
    outlined
    :show-size="1000"
  ></v-file-input>

      <v-btn
        :disabled="!valid"
        color="accent"
        class="mr-4"
        @click="validate"
      >
        Validate
      </v-btn>
  
      <v-btn
        color="warning"
        class="mr-4"
        @click="reset"
      >
        Reset Form
      </v-btn>
  
    </v-form>
    <v-snackbar
        v-model="snackbar"
      >
        {{ text }}
  
        <template v-slot:action="{ attrs }">
          <v-btn
            color="green"
            text
            v-bind="attrs"
            @click="snackbar = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
  </div>
  </template>
  
  <script>
  import NewsTellerServices from '../services/NewsTellerServices';
  
  export default {
    data: () => ({
        snackbar: false,
        text: '',
        valid: false,
        title: '',
        file: '',
      }),
  
      methods: {
  
       async validate () {
          this.valid = this.$refs.form.validate();
          if(!this.valid) {
            this.text = 'Please fill the form correctly'
          }
          else {
          let newsTeller = {
            title: this.title,
          }

          //let input = this.$refs.fileInput
          let fileString = this.file
          let formData = new FormData()
          formData.append('file', fileString)
          let id = null
          id = await NewsTellerServices.add(newsTeller);
          //console.log(id);
          if(id != null) {
              NewsTellerServices.putFile(id, formData);
              this.text = 'News Teller Post Created Successfuly';
        }
        
        this.reset();
        }

        this.snackbar = true;
        },

        reset () {
          this.$refs.form.reset()
        },
      },
      watch() {
      },
      created () {
      }
  }
  </script>
  