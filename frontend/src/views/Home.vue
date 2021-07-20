<template>
  <div class="home">
    <Carousel/>
    <h3></h3>
    <h1 style="text-align: center; text-transform: uppercase; color:#ffff;">Products</h1>
    <h3></h3>
    <ProductGrid/>

  </div>
</template>

<script>
// @ is an alias to /src
import Carousel from '@/components/Carousel.vue'
import ProductGrid from '@/components/ProductGrid.vue'

export default {
  name: 'Home',
  components: {
    Carousel,
    ProductGrid,
  }
};
</script>

<style >

#body{

  background-color: black;
   border-spacing: 15px;
}

</style>

