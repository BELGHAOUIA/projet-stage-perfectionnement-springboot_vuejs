<template>
    <div
    class="pa-16"
    >
        <v-card class="rounded-lg"
        elevation="16"
        >
            <v-row  class="inline-flex justify-space-around">
              <v-col>
                <v-img width="400" height="500" src="./../assets/20944201.jpg"></v-img>
                    </v-col>
                    <v-col class="pa-10" 
                    style="background-color: #5C6BC0;">
                      <v-form 
                      class="form  pt-lg-16"
                     ref="form"
                    v-model="valid"
                    lazy-validation
                    >
                    
         <v-text-field
         v-model="usernName"
         :rules="[v => !!v || 'Username is required']"
            counter
            label="User Name"
            required
            color="white"
            ></v-text-field>
            
            <v-text-field
            color="white"
          v-model="password"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[v => !!v || 'Password is required']"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="Password"
          counter
          @click:append="show1 = !show1"
          ></v-text-field>
          <v-card-actions>
            
            <v-btn 
            :loading="loading"
            elevation="16"
            :disabled="!valid || loading"
            color="accent"
            @click="load()"
            tile
            x-large
            >
            <v-icon x-large>
              mdi-key
            </v-icon>
            Sign In
          </v-btn>
          
        </v-card-actions>
        
      </v-form>
    </v-col>
    </v-row>
  </v-card>
   
    <v-snackbar
        v-model="snackbar"
        >
        {{ text }}
        
        <template v-slot:action="{ attrs }">
          <v-btn
            :color="warning"
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
import AdminAccountService from "./../services/AdminAccountServices";
export default {
    data () {
        return {
                snackbar: false,
                text: '',
                valid: false,
                show1: false,
                password: '',
                usernName: '',
                loading: false,
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
               // await AdminAccountService.add({adminUserName: this.usernName, password: this.password})
            if(await AdminAccountService.existsByIdAndPassword(this.usernName, this.password)) {
                    this.$session.start()
                    this.$session.set('key', this.usernName)
                    //console.log(this.$session.get('key'))
                    //this.$router.push('/dashboard')
                    this.$router.go()
            }
            else {
                this.text ='Account Does not Exists In The Data Base'
                this.snackbar = true;
            }
             }
            },
               if() {
                if (!this.$session.exists()) {
                  this.$router.push('/')
                }
                else if (this.$session.exists()){
                  this.$router.push('/dashboard')
                }
              },
              load () {
                  this.loading = true
                  setTimeout(() => (this.loading = false), 3000)
                  this.validate();
                },
              }
              ,
              created () {
                this.if();
            },

};
</script>
<style>
  .custom-loader {
    animation: loader 1s infinite;
    display: flex;
  }
  @-moz-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-webkit-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-o-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
</style>