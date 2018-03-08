<template>
    <div class="row">
      <div class="col-lg-6 col-md-5">
        <user-card :user="user">

        </user-card>
        <members-card :user="user">

        </members-card>
      </div>
      <div class="col-lg-6 col-md-7">
        <PaperTable :title="'Hozzászólásai'" :subTitle="''" :data="user.events" :columns="['title', 'address', 'date', 'price', 'duration', 'active']">

        </PaperTable>
        <PaperTable :title="'Események'" :subTitle="'ahol résztvesz'" :data="user.events" :columns="['title', 'address', 'date', 'price', 'duration', 'active']">

        </PaperTable>
        <PaperTable :title="'Előadások'" :data="user.events" :columns="['title', 'address', 'date', 'price', 'duration', 'active']">

        </PaperTable>
        <edit-profile-form v-if="this.$store.state.loggedInUserName === user.username" :user="user">

        </edit-profile-form>
      </div>
    </div>
</template>
<script>
  import EditProfileForm from './UserProfile/EditProfileForm.vue'
  import UserCard from './UserProfile/UserCard.vue'
  import MembersCard from './UserProfile/MembersCard.vue'
  import PaperTable from '../../UIComponents/PaperTable'
  import {AXIOS} from '../../http-common'
  export default {
    components: {
      EditProfileForm,
      UserCard,
      MembersCard,
      PaperTable
    },
    props: ['username'],
    data () {
      return {
        user: {}
      }
    },
    created () {
      let self = this
      AXIOS.get('http://localhost:8089/api/user/' + this.username)
        .then(function (response) {
          self.user = response.data
        })
    }
  }

</script>
<style>

</style>
