<template>
    <div >
        <v-carousel
        cycle
        hide-delimiter-background
        show-arrows-on-hover
        >
      <v-carousel-item
        v-for="(slide, i) in slides"
        :key="i"
      >
        <v-parallax 
        src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"
        >

            <v-row
            align="center"
            justify="center"
            >
            <div>
                <h1>
                    {{ slide }}
                </h1>
            </div> 
        </v-row>
    </v-parallax>
    </v-carousel-item>
    </v-carousel>
    <v-spacer></v-spacer>
   
    <v-row
    align="bottom"
    justify="space-around"
  >
  <router-link style="text-decoration: none;  color: inherit;" to="/SignUp">
    <v-btn
    tile
    x-large
    color="primary"
    >
    <v-icon x-large left>
        mdi-emoticon-wink-outline
    </v-icon>
    <v-spacer></v-spacer>
    Sign Up
</v-btn>
</router-link >

<router-link style="text-decoration: none;  color: inherit;" to="/SignIn">
    <v-btn
      tile
      x-large
      color="primary"
    >
      <v-icon x-large left>
        mdi-emoticon-cool-outline
      </v-icon>
          Sign In
    </v-btn>
</router-link>
  </v-row>

    </div>
  </template>

<script>
export default {
  data () {
    return {
      slides: [
        'NATIONAL & REGIONAL LEADER IN THE TEXTILE INDUSTRY',
        'EXPERIENCED GARMENT MANUFACTURER FOR YOUR  SUCCESS',
        'INNOVATIVE & SUSTAINABLE MANUFACTURING PROCESSES',
      ],
    }
  },
}
</script>