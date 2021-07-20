<template>
  <div class="login">
      <SignIn />
  </div>
</template>

<script>
// @ is an alias to /src
import SignIn from '@/components/SignIn.vue'

export default {
   name: 'Login',
  components: {
    SignIn,
  }
};
</script>
