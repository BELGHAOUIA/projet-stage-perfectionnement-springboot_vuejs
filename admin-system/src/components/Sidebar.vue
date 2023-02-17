<template>
  <v-navigation-drawer v-model="drawer" width="315" app>
    <div 
      class="pa-10"
    >
      <div class="text-center">
          <v-icon dense x-large>mdi-account-cog</v-icon>
        <h2 class="black--text">SUPER ADMIN</h2>
      </div>
    </div>
    <v-divider></v-divider>
    <v-row justify="center">
    <v-expansion-panels accordion>
      <v-expansion-panel>
        <v-expansion-panel-header>
          <v-list-item>
        <v-list-item-icon>
          <v-icon :color="color" dense large>mdi-plus</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Create & Add</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
        </v-expansion-panel-header>
        <v-divider></v-divider>

        <v-expansion-panel-content>
          <v-list>
    <router-link style="text-decoration: none; color: inherit;" to="/CreateEmployeeAccount">
      <v-list-item link>
        <v-list-item-icon>
          <v-icon>mdi-account-multiple-plus</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Employee Account</v-list-item-title>
        </v-list-item-content>
        
      </v-list-item>
    </router-link>

    <router-link style="text-decoration: none; color: inherit;" to="/CreateJobOffer">
      <v-list-item link>
        <v-list-item-icon>
          <v-icon>mdi-briefcase-plus</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Job Offer</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </router-link>


    <router-link style="text-decoration: none; color: inherit;" to="/CreateNewsTeller">
      <v-list-item link>
        <v-list-item-icon>
          <v-icon >mdi-text-box-plus</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>News Teller</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </router-link>

    </v-list>

        </v-expansion-panel-content>
      </v-expansion-panel>


      <v-expansion-panel>
        <v-expansion-panel-header>
          <v-list-item>
        <v-list-item-icon>
          <v-icon :color="color" dense large>mdi-eye</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>View & Edit</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
        </v-expansion-panel-header>
        <v-divider></v-divider>
        <v-expansion-panel-content>

          <v-list>

    <router-link style="text-decoration: none; color: inherit;" to="/ViewEmployeeAccounts">
      <v-list-item link>
        <v-list-item-icon>
          <!--<v-badge
          :content="adminAccountsCount"
        color="green"
        overlap
      >-->
          <v-icon>mdi-account-eye</v-icon>
        <!--</v-badge>-->
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>Employees' Accounts</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </router-link>

    <router-link style="text-decoration: none; color: inherit;" to="/ViewNewsTeller">
      <v-list-item link>
        <v-list-item-icon>
         <!-- <v-badge
          :content="employeeAccountsCount"
        color="green"
        overlap
      >-->
          <v-icon>mdi-text-box-edit</v-icon>

        <!--</v-badge>-->
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>News Teller List</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </router-link>

    <router-link style="text-decoration: none; color: inherit;" to="/ViewJobOffers">
      <v-list-item link>
        <v-list-item-icon>
         <!-- <v-badge
          :content="employeeAccountsCount"
        color="green"
        overlap
      >-->
          <v-icon>mdi-briefcase-eye</v-icon>

        <!--</v-badge>-->
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Job Offers List</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </router-link>
    
    <router-link style="text-decoration: none; color: inherit;" to="/ViewJobsApplications">
      <v-list-item link>
        <v-list-item-icon>
         <!-- <v-badge
          :content="employeeAccountsCount"
        color="green"
        overlap
      >-->
          <v-icon>mdi-briefcase-check</v-icon>

        <!--</v-badge>-->
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Job Offers Applications</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </router-link>
    
    <router-link style="text-decoration: none; color: inherit;" to="/ViewInternshipApplications">
      <v-list-item link>
        <v-list-item-icon>
         <!-- <v-badge
          :content="employeeAccountsCount"
        color="green"
        overlap
      >-->
          <v-icon>mdi-application-edit</v-icon>

        <!--</v-badge>-->
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Internship Applications</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </router-link>
    

    </v-list>

        </v-expansion-panel-content>
      </v-expansion-panel>


      <v-expansion-panel>
        <v-expansion-panel-header>
          <v-list-item>
        <v-list-item-icon>
          <v-icon :color="color" dense large>mdi-account</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Profile</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
        </v-expansion-panel-header>
        <v-divider></v-divider>
        <v-expansion-panel-content>

          <v-list>

        <v-list-item link                 
         @click.stop="dialog = true, setTargetedID()"
         >
        <v-list-item-icon>
          <v-icon>mdi-account-details</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Change Password</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link @click="logout">
        <v-list-item-icon>
          <v-icon>mdi-logout</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Log Out</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

    </v-list>

        </v-expansion-panel-content>
      </v-expansion-panel>


    </v-expansion-panels>
  </v-row>

  <v-dialog
      v-model="dialog"
      max-width="600px"
    >
      <v-card
      class="pa-5"
>
<v-form 
    class="form text-center mr-16 ml-16"
    ref="form"
    v-model="valid"
    lazy-validation
  >
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
      color="accent"
      class="mr-4"
      @click="validate"
    >
      Validate
    </v-btn>
    <v-btn
      color="warning"
      class="mr-4"
      @click="dialog = false"
    >
      Close
    </v-btn>
    </v-form>
</v-card>
</v-dialog>


  </v-navigation-drawer>
</template>

<script>
import AdminAccountServices from '../services/AdminAccountServices';
export default {
  name: "Sidebar",
  data() {
    return {
      adminID: null,
      rulePass: [
        value => !!value || 'Password is required.',
        v => ( v!=null && v.length >= 8) || 'Min 8 characters'
      ],
      show1: false,
      dialog: false,
      valid: null,
      password: "",
      color: 'accent'

      
    }
  },
  props: {
    drawer : {
      type: Boolean,
    }
  },

  methods: {
    logout: function () {
      this.$session.destroy()
      this.$router.go()
    },
    setTargetedID() {
      this.adminID = this.$session.get('key');
    },

    async validate() {
     // console.log(this.adminID,"    ", this.password)
      if(this.valid) {
        await AdminAccountServices.update(this.adminID,{password: this.password});
        this.$session.destroy()
        this.$router.go()
      }
    }
  }
};
</script>

<style scoped>
v-navigation-drawer {
  width: 100rem;
}

</style>
