<template>
  <v-app id="inspire">
    <Sidebar v-if="display" :drawer="drawer" />
    <Topbar v-if="display" @drawerEvent="drawer = !drawer" />
    <v-main style="background: #f5f5f540">
      <v-container class="py-8 px-6" fluid>
        <router-view />
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Sidebar from "./components/Sidebar";
import Topbar from "./components/Topbar";

export default {
  name: "App",
  components: { Topbar, Sidebar },
  data: () => ({
    drawer: null,
    display: null
  }),
  methods: {
    if() {
    if (!this.$session.exists()) {
      this.display = false
      this.$router.push('/')
    }
    else if (this.$session.exists()){
      this.display = true
    }
  }
  }
  ,
  created () {
     this.if();
},

};
</script>
