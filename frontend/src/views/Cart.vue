<template>
  <div class="cart" >
      <Cart />
  </div>
</template>

<script>
// @ is an alias to /src
import Cart from '@/components/Cart.vue'

export default {
   name: 'Login',
  components: {
    Cart,
  }
};
</script>


<style >

#body{

  background-color: black;

}

</style>