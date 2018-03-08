<template>
  <div class="card">
    <div class="header">
      <h4 class="title">Profil szerkesztése <br> <small>Nyugi, ezt csak te látod</small></h4>
    </div>
    <div class="content">
      <form>
        <div class="row">
          <div class="col-md-5">
            <fg-input type="email"
                      label="Email"
                      :disabled="true"
                      v-model="user.email">
            </fg-input>
          </div>
          <div class="col-md-3">

            <fg-input type="text"
                      label="Felhasználónév"
                      v-model="user.username">
            </fg-input>
          </div>
          <div class="col-md-4">
            <fg-input type="text"
                      label="Foglalkozás"
                      v-model="user.profession">
            </fg-input>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <fg-input type="text"
                      label="Kereszt név"
                      v-model="user.name">
            </fg-input>
          </div>
          <div class="col-md-6">
            <fg-input type="text"
                      label="Család név"
                      v-model="user.lastName">
            </fg-input>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <fg-input type="text"
                      label="Weboldal"
                      v-model="user.website">
            </fg-input>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="form-group">
              <label>Bemutatkozó</label>
              <textarea rows="5" class="form-control border-input"
                        placeholder="Here can be your description"
                        v-model="user.description">

              </textarea>
            </div>
          </div>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-info btn-fill btn-wd" @click.prevent="updateProfile">
            Update Profile
          </button>
        </div>
        <div class="clearfix"></div>
      </form>
    </div>
  </div>
</template>
