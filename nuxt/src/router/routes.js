export default [
  {
    path: '/',
    component: () => import('layouts/default'),
    children: [
      { path: '', component: () => import('pages/index') },
      { path: '/bejelentkezes', component: () => import('pages/auth') },
      { path: '/projekt', component: () => import('pages/theProject') },
      { path: '/kapcsolat', component: () => import('pages/contact') },
      { path: '/esemenyek', component: () => import('pages/events') },
      { path: '/felhasznalo/:username', props: true, component: () => import('pages/profile') },
      { path: '/esemeny/:id', props: true, component: () => import('pages/event') },
      { path: '/admin', props: true, component: () => import('pages/adminPanel') }
    ]
  },

  { // Always leave this as last one
    path: '*',
    component: () => import('pages/404')
  }
]
