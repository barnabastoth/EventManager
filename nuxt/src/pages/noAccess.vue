<template>
  <div class="fixed-center text-center">
    <p>
      <img
        src="~assets/sad.svg"
        style="width:30vw;max-width:150px;"
      >
    </p>
    <p class="text-faded">Sajnos ezt az oldalt nem engedhetem, hogy megnyisd. Nincs jogosultságod hozzá.<strong>(404)</strong></p>
    <q-btn
      color="primary"
      style="width:200px;"
      @click="$router.push('/')"
    >Vissza a főoldalra</q-btn>
  </div>
</template>

<script>
export default {
  name: 'no-access'
}
</script>

<style scoped>

</style>
