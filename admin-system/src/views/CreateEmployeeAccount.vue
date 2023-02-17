<template>
    <div>
      <v-subheader class="py-0 d-flex justify-space-between rounded-lg">
              <h3>Creating An Employee's Account</h3>
          </v-subheader>
          <v-divider></v-divider>
          <br>
    <v-form 
      class="form text-center mr-16 ml-16"
      ref="form"
      v-model="valid"
      lazy-validation
      @submit.prevent="validate"
    >
  
    <v-col class="d-lg-flex ">

            <v-text-field
            v-model="firstName"
            :rules="nameRules"
            counter
            label="First name"
            required
            outlined
            prepend-icon="mdi-menu-right-outline"
            ></v-text-field>
            
            <v-text-field
            v-model="lastName"
            :rules="nameRules"
            counter
            label="Last name"
            required
            outlined
            prepend-icon="mdi-menu-right-outline"
            
            ></v-text-field>
          </v-col>
            
      <v-text-field
          v-model="phoneNumber"
          :counter="8"
          :rules="phoneNumberRules"
          label="Phone Number"
          required
          outlined
          prepend-icon="mdi-phone"
        ></v-text-field>

      <v-select
        :rules="[v => !!v || 'Item is required']"
          v-model="department"
          :items="departments"
          menu-props="auto"
          hide-details
          label="Select Department"
          single-line
          required
          outlined
          prepend-icon="mdi-account-group"
        ></v-select>
<br>
        <v-text-field
            v-model="role"
            :rules="[v => !!v || 'Role is required']"
            label="Employee Role"
            required
            outlined
            prepend-icon="mdi-account-tie-woman"
          ></v-text-field>


      <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            required
            outlined
            prepend-icon="mdi-email"
          ></v-text-field>

      <v-text-field
              v-model="password"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="rulePass"
              :type="show1 ? 'text' : 'password'"
              name="input-10-1"
              label="Password"
              counter
              @click:append="show1 = !show1"
              outlined
              prepend-icon="mdi-key"
        ></v-text-field>
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
  
  import EmployeeAccountServices from '../services/EmployeeAccountServices';
  
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
                  departments: [
                      'Design Department',
                      'Production Department',
                      'Marketing Department',
                      'Human Resources Department',
                      'Finance Department',
                      'Procurement Department',
                      'Information Technology Department'
                    ],
                  firstName: '',
                  lastName: '',
                  phoneNumber: '',
                  email: '',
                  password: '',
                  department: '',
                  role: '',
      }),
  
      methods: {
  
        async validate () {
          this.valid = this.$refs.form.validate();
          if(!this.valid) {
            this.text = 'Please fill the form correctly'
            this.color= 'red'
          }
          else { 
            if(await EmployeeAccountServices.existsById(this.email)) {
              this.text = this.email + ' Account Already Exists In The Data Base'
              this.color= 'red'
            }
            else {
              
          const employee = {
            firstName: this.firstName,
            lastName: this.lastName,
            phoneNumber: this.phoneNumber,
            email: this.email,
            password: this.password,
            employeeDepartment: this.department,
            employeeRole: this.role,
            avatar: null
          }
          if (await EmployeeAccountServices.add(employee)) {
            this.text = this.firstName + ' ' + this.lastName + ' Account Created Successfuly';
            this.color= 'green'
          }
         /* let input = this.$refs.fileInput
        let file = input.files[0]
        this.formData = new FormData()
        this.formData.append('file', file)*/
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
  