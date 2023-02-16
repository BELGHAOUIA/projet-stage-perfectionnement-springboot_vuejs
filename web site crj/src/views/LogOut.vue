<script>
export default {
    methods: {
        logout() {
            if(this.$session.exists()) {
            this.$session.destroy()
               this.$router.go()
        }
        else {
            this.$router.push('/')
        }
      },
    },
    created() {
       this.logout()
    }
}
</script>