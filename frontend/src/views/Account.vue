<template>
  <div class="account">
      <UserAccount />

  </div>
</template>

<script>
// @ is an alias to /src
import UserAccount from '@/components/UserAccount.vue'


export default {
   name: 'Account',
  components: {
    UserAccount,
  }
};
</script>
