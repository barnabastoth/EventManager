<template>
  <div class="wrapper">
    <side-bar type="sidebar" :sidebar-links="$sidebar.sidebarLinks"></side-bar>
    <notifications></notifications>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <Content @click.native="toggleSidebar">

      </Content>

      <content-footer></content-footer>
    </div>
  </div>
</template>

<script>
  import TopNavbar from '../src/components/Dashboard/Layout/TopNavbar.vue'
  import ContentFooter from '../src/components/Dashboard/Layout/ContentFooter.vue'
  import Content from '../src/components/Dashboard/Layout/Content.vue'
  export default {
    components: {
      TopNavbar,
      ContentFooter,
      Content
    },
    methods: {
      toggleSidebar () {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    }
  }

</script>
