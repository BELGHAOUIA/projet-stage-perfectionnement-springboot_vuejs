<template>
    <div style="background-image: url(https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg);">
      <v-content>
          <v-container class="fill-height" fluid>
            <v-row align="center" justify="center">
              <v-card class="ma-9 rounded-lg" elevation="15">
                <v-row>
                  <v-col>
                    
                    <v-form 
                    class="form pa-16"
                    ref="form"
                    v-model="valid"
                    lazy-validation
                    >
                    
        <v-select
        outlined
        :rules="[v => !!v || 'Item is required']"
          v-model="accountType"
          :items="types"
          prepend-icon="mdi-account"
          menu-props="auto"
          hide-details
          label="Select Account Type"
          single-line
          required
        ></v-select>
      <br>
          <v-file-input
          outlined
          v-model="file"
          v-if="accountType == 'Employee'"
            accept="image/png, image/jpeg"
              placeholder="Pick an avatar"
              prepend-icon="mdi-camera"
              label="Avatar"
              ref="fileInput"
              :rules="[v => !!v || 'Image is required']"
              @change="uploadFile"
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
          :rules="[v => !!v || 'Password is required']"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="Password"
          counter
          @click:append="show1 = !show1"
          ></v-text-field>
          <v-card-actions justify="center">
            
            <v-btn
            x-large
            :disabled="!valid"
            color="accent"
            tile
            @click="validate"
            >
            Sign In
          </v-btn>
          
        </v-card-actions>
        
      </v-form>
      
    </v-col>
  </v-row>
  
</v-card>
</v-row>
</v-container>
</v-content>
    <v-snackbar
        v-model="snackbar"
      >
        {{ text }}
  
        <template v-slot:action="{ attrs }">
          <v-btn
            :color="red"
            text
            v-bind="attrs"
            @click="snackbar = false"
          >
            Close
          </v-btn>
        </template>
      </v-snackbar>
      <br><br><br><br><br>
    </div>
</template>

<script>
import EmployeeAccountService from "../services/EmployeeAccountServices";
import InternAccountService from "../services/InternAccountServices";
import JobSeekerAccountService from "../services/JobSeekerAccountServices";
export default {
    data () {
        return {
                snackbar: false,
                text: '',
                valid: false,
                show1: false,
                emailRules: [
                  v => !!v || 'E-mail is required',
                  v => /.+@.+/.test(v) || 'E-mail must be valid',
                ],
                types: [
                  "Employee","Job Seeker", "Intern"
                ],
                password: '',
                email: '',
                accountType: '',
                file: ''
        }
    },
    methods: {
  
    async validate () {
            this.valid = this.$refs.form.validate();
            if(!this.valid) {
            this.text = 'Please fill the form correctly'
            this.snackbar = true;
            }
            else { 
               if(this.accountType == 'Employee'){
            if(await EmployeeAccountService.existsByIdAndPassword(this.email, this.password)) {
              const e = await EmployeeAccountService.getById(this.email)

              let fileString = this.file
              let formData = new FormData()
              formData.append('file', fileString)

              await EmployeeAccountService.putAvatar(this.email, formData)

                    this.$session.start()
                    this.$session.set('key', {
                    id: this.email,
                    UserName: e.firstName,
                    accountType: this.accountType
                  })
                    //console.log(this.$session.get('key'))
                    this.$router.go()
            } 
            else {
                  this.text ='Account Does not Exists In The Data Base'
                  this.snackbar = true;
                }
              }
             else if(this.accountType == 'Job Seeker'){
            if(await JobSeekerAccountService.existsByIdAndPassword(this.email, this.password)) {
              const e = await JobSeekerAccountService.getById(this.email)
                    this.$session.start()
                    this.$session.set('key', {
                    id: this.email,
                    UserName: e.firstName,
                    accountType: this.accountType
                  })
                    //console.log(this.$session.get('key'))
                    this.$router.go()
                   // this.$router.push('/dashboard')
            } 
            else {
                  this.text ='Account Does not Exists In The Data Base'
                  this.snackbar = true;
                }
              }

              else if(this.accountType == 'Intern'){
            if(await InternAccountService.existsByIdAndPassword(this.email, this.password)) {
              const e = await InternAccountService.getById(this.email)
                    this.$session.start()
                    this.$session.set('key', {
                    id: this.email,
                    UserName: e.firstName,
                    accountType: this.accountType
                  })
                    //console.log(this.$session.get('key'))
                    this.$router.go()
                   // this.$router.push('/dashboard')
            } 
            else {
                  this.text ='Account Does not Exists In The Data Base'
                  this.snackbar = true;
                }
              }
             }
            },
            go() {
              if(this.$session.exists())
              this.$router.push('/')
            }

        },
        created() {
          this.go()
        }
};
</script>