<template>
  <v-app-bar app elevate-on-scroll elevation="3">
    <v-app-bar-nav-icon @click="$emit('drawerEvent')"></v-app-bar-nav-icon>
    <v-spacer />
    <!--<v-col lg="6" cols="10">
      <v-form>
        <v-text-field
          class="p-0 m-0 mt-6"
          full-width
          dense
          append-icon="mdi-magnify"
          outlined
          rounded
          placeholder="Search"
        />
      </v-form>
    </v-col>-->
      <v-switch
        v-model="$vuetify.theme.dark"
        inset
      ></v-switch>
  </v-app-bar>
</template>

<script>
export default {
  name: "Topbar",
  data() {
    return {}
  }
};
</script>

<style scoped></style>
