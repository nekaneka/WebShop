<template>
  <div class="register">
      <SignInSignUp />
  </div>
</template>

<script>
// @ is an alias to /src
import SignInSignUp from '@/components/SignIn_SignUp.vue'

export default {
   name: 'Register',
  components: {
    SignInSignUp,
  }
};
</script>
