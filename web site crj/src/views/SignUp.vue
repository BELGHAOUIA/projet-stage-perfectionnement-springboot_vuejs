<template>
    <div class="pa-14" style="background-image: url(https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg);">
            <v-row align="center" justify="center">
              <v-card class=" pa-16 rounded-lg"
               elevation="15">
    <v-form 
      class="form text-center "
      ref="form"
      v-model="valid"
      lazy-validation
      @submit.prevent="validate"
    >
  <v-col class="d-lg-flex">

    <v-text-field
    outlined
    prepend-icon="mdi-arrow-right"
    v-model="firstName"
    :rules="nameRules"
    counter
    label="First name"
            required
          ></v-text-field>

          <v-text-field
          outlined
          prepend-icon="mdi-arrow-right"
            v-model="lastName"
            :rules="nameRules"
            counter
            label="Last name"
            required
          ></v-text-field>
        </v-col>
  
      <v-text-field
      outlined
          prepend-icon="mdi-phone"
          v-model="phoneNumber"
          :counter="8"
          :rules="phoneNumberRules"
          label="Phone Number"
          required
        ></v-text-field>

        <v-select
        outlined
          prepend-icon="mdi-account"
        :rules="[v => !!v || 'Item is required']"
          v-model="accountType"
          :items="types"
          menu-props="auto"
          hide-details
          label="Select Account Type"
          single-line
          required
        ></v-select>
<br>
        <v-file-input
        outlined
          prepend-icon="mdi-paperclip"
        v-if="accountType == 'Job Seeker'"
          v-model="file"
          :accept="pdf"
          :rules="[v => !!v || 'File is required']"
          color="deep-purple accent-4"
          counter
          label="Select your CV"
          :show-size="1000"
        ></v-file-input>
      
      <v-text-field
      outlined
          prepend-icon="mdi-email"
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            required
          ></v-text-field>

      <v-text-field
      outlined
          prepend-icon="mdi-key"
              v-model="password"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="rulePass"
              :type="show1 ? 'text' : 'password'"
              name="input-10-1"
              label="Password"
              counter
              @click:append="show1 = !show1"
        ></v-text-field>
      <v-btn
        :disabled="!valid"
        color="accent"
        class="mr-4"
        tile
        @click="validate"
        large
      >
        Validate
      </v-btn>
      <v-btn
        color="warning"
        class="mr-4"
        tile
        @click="reset"
        large
      >
        Reset Form
      </v-btn>
  
    </v-form>
    </v-card>
    </v-row>
    <v-snackbar
        v-model="snackbar"
      >
        {{ text }}
  
        <template v-slot:action="{ attrs }">
          <v-btn
            :color="color"
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
  
  import JobSeekerAccountServices from '../services/JobSeekerAccountServices';
  import InternAccountServices from '../services/InternAccountServices';
  
  export default {
    name: 'CreateEmployeeAccount',
    data: () => ({
      color: 'green',
      snackbar: false,
        text: '',
        vertical: true,
        valid: false,
        show1: false,
          rulePass: [
          value => !!value || 'Password is required.',
            v => ( v!=null && v.length >= 8) || 'Min 8 characters'
          ],
      nameRules: [
        v => !!v || 'Name is required',
      ],
      phoneNumberRules: [
        v => !!v || 'Phone Number is required',
        v => v.length == 8 || 'Phone Number must be 8 digits',
      ],
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],
      types: [
                "Job Seeker", "Intern"
             ],
       accountType: '',
      firstName: '',
      lastName: '',
      phoneNumber: '',
      email: '',
      password: '',
      file: ''
    }),
    
    methods: {
      
      async validate () {
        this.valid = this.$refs.form.validate();
        if(!this.valid) {
          this.text = 'Please fill the form correctly'
          this.color= 'red'
        }
        else { 

            const person = {
              firstName: this.firstName,
              lastName: this.lastName,
              phoneNumber: this.phoneNumber,
              email: this.email,
              password: this.password
            }

            if(this.accountType == "Job Seeker" && await JobSeekerAccountServices.existsById(this.email) ||
            this.accountType == "Intern" && await InternAccountServices.existsById(this.email)
            ) {
              this.text = this.email + ' Account Already Exists In The Data Base'
              this.color= 'red'
            }

            else {
              
          if(this.accountType == 'Intern') {
            if (await InternAccountServices.add(person)) {
              this.text = this.firstName + ' ' + this.lastName + ' Account Created Successfuly';
              this.color= 'green'
            }
          }
          else if(this.accountType == 'Job Seeker') {
            if (await JobSeekerAccountServices.add(person)) {
              
              let fileString = this.file
              let formData = new FormData()
              formData.append('file', fileString)

              await JobSeekerAccountServices.putFile(this.email, formData)
            this.text = this.firstName + ' ' + this.lastName + ' Account Created Successfuly';
            this.color= 'green'
          }
          }
        }
      }
      this.snackbar = true;
      this.reset();
        },

        uploadFile() {
        let input = this.$refs.fileInput
        let file = input.files[0]
        this.formData = new FormData()
        this.formData.append('file', file)
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
  