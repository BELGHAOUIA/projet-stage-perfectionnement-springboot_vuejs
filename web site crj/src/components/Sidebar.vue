<template>
  <v-navigation-drawer v-model="drawer" class="d-flex d-md-none" app>
      <v-list-group
      v-for="item in items"
      :key="item.title"
      v-model="item.active"
      :prepend-icon="item.action"
      no-action
      >
      
      <v-avatar v-if="item.avatar != null">
      <img
        src="https://cdn.vuetifyjs.com/images/john.jpg"
        alt="John"
      >
    </v-avatar>

      <template v-slot:activator>
        <v-list-item-content>
          <router-link style="text-decoration: none;  color: inherit;" :to="item.route">
            <v-list-item-title>
                <v-avatar v-if="item.action == ''" class="mr-6"  width="35" height="35">
                  <img :src="dataUrl"  style="border: 1px solid black">
                </v-avatar>
                {{ item.title }}

            </v-list-item-title>
        </router-link>
          </v-list-item-content>
       
        </template>
        
        <v-list-item
        link
        v-for="child in item.items"
        :key="child.title"
        background-color="primary"
        >
        <v-fade-transition
        >
        <router-link 
        style="text-decoration: none; color: inherit;"
        :to="child.route"
        link
        >
        
        <v-list-item-content>
          <v-list-item-title
          >
          <v-icon small>
            mdi-square
          </v-icon>
          {{child.title}}
            </v-list-item-title>
          </v-list-item-content>
        </router-link>
    </v-fade-transition>
        </v-list-item>
    
    
  </v-list-group>
  </v-navigation-drawer>
</template>

  <script>
  import EmployeeAccountServices from '../services/EmployeeAccountServices';

  export default {
    props: {
    drawer : {
      type: Boolean,
    }
  },
    data: () => ({
      items: [
        {
          action: 'mdi-home',
          active: true,
          title: 'Home',
          route: '/'
        },
        {
          action: 'mdi-briefcase',
          items: [
            { title: 'News Teller' ,
             route: "/NewsTeller"},
        ],
          title: 'Employee',
          route: ""
        },
        {
          action: 'mdi-human',
          items: [
            { title: 'Open Jobs' ,
            route: "/OpenJobs"},
            { title: 'Applications Status' ,
            route: "/OpenJobStatus"},
          ],
          title: 'Job Seeker',
          route: ""
        },
        {
          action: 'mdi-school',
          items: [
            { title: 'Application Form' ,
            route: "/InternshipApplicationForm"},
            { title: 'Application Status' ,
            route: "/ApplicationFormStatus"},
            
          ],
          title: 'Intern',
          route: ""
        },
        {
          action: 'mdi-account',
          items: [
            { title: 'Sign Up' ,
            route: "/SignUp"},
            { title: 'Sign In' ,
            route: "/SignIn"},
          ],
          title: 'Account',
          route: ""
        },
      ],
      dataUrl: ''
    }),
    methods: {
      async sessionStarted() {
        if (this.$session.exists()) {
           this.items[4] = {
          action: 'mdi-account',
          items: [
            { title: 'Log Out' ,
              route: "/LogOut"},
          ],
          title: this.$session.get('key').UserName,
          route: ""
        }
        if(this.$session.get('key').accountType == 'Employee'){
          this.items[4].action = "";
          console.log("employee?" + this.$session.get('key').accountType == 'Employee')
          await EmployeeAccountServices.getFile(this.$session.get('key').id) 
          .then(response => {
                    const blob = new Blob([response]);
                    const objectUrl = URL.createObjectURL(blob);
                    this.dataUrl = objectUrl;
                    objectUrl.revokeObjectURL
          })
        }
      }
    },
    },
    created() {
      this.sessionStarted()
    }
  }
</script>


<style scoped>
v-navigation-drawer {
  width: 100rem;
}

</style>
