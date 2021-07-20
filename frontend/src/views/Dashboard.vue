<template>
  <div class="account">
      <Dashboard />

  </div>
</template>

<script>
// @ is an alias to /src
import Dashboard from '@/components/Dashboard.vue'


export default {
   name: 'Account',
  components: {
    Dashboard,
  }
};
</script>

