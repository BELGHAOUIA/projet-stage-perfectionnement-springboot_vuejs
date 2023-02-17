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
  >

  <v-select
      v-model="adminRole"
      :items="items"
      :rules="[v => !!v || 'Admin Role is required']"
      label="Admin Role"
      required
    ></v-select>
    
    <v-text-field
      v-model="adminUserName"
      counter
      :rules="nameRules"
      label="Admin User Name"
      required
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
      ></v-text-field>
    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="validate"
    >
      Validate
    </v-btn>

    <v-btn
      color="error"
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

import EmployeeAccountServices from '../services/EmployeeAccountServices';

export default {
  name: 'CreateEmployeeAccount',
  data: () => ({
      snackbar: false,
      text: '',
      vertical: true,
      valid: false,
      nameRules: [
        v => !!v || 'Admin User Name is required',
        v => ( v!=null && v.length >= 8) || 'Name must be at least be 8 characters',
      ],
      show1: false,
        rulePass: [
        value => !!value || 'Password is required.',
          v => ( v!=null && v.length >= 8) || 'Min 8 characters'
        ],
      items: [
        'Job Admin',
        'News Teller Admin',
        'Internship Admin',
      ],
      password: "",
      adminUserName: "",
      adminRole: this.$sharedData.message,
    }),

    methods: {

     async validate () {
        this.valid = this.$refs.form.validate();
        if(!this.valid) {
          this.text = 'Please fill the form correctly'
        }
        else {
          if(await EmployeeAccountServices.getAccountById(this.adminRole)) {
            this.text = this.adminRole + ' Account Already Exists In The Data Base'
          }
          else {
        const adminAccount = {
          adminRole: this.adminRole,
          adminUserName: this.adminUserName,
          password: this.password
        }
        EmployeeAccountServices.addAdminAccount(adminAccount);
        this.text = this.adminRole + ' Account Created Successfuly';
      }
    }
      this.snackbar = true;
      this.reset();
      },
    
      reset () {
        this.$refs.form.reset()
      },
    },

    computed: {
    valid() {
      return this.$refs.form.validate();
    }
  },
    watch() {
    },
    created () {
    }
}
</script>
