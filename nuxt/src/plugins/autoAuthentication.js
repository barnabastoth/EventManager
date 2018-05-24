import AXIOS from 'axios'

export default ({ app, router, store, Vue }) => {
  AXIOS.defaults.baseURL = 'http://localhost:8089'
  store.dispatch('logInUserWithToken').then(() => {
    store.dispatch('loadSiteSettings')
  })
}
