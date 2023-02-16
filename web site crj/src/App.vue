<template>
  <v-app>
    <Sidebar :drawer="drawer"/>
    <Topbar @drawerEvent="drawer = !drawer"/>
    <v-main style="background: #f5f5f540">
      <v-container fluid>
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
    drawer: false,
  }),


};
</script>

<style scoped>
@media (max-width: 700px) {
  SideBar {
    display: none !important; 
  }
}
</style>
