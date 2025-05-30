import { defineStore } from 'pinia'
import type { SearchForm } from '@/types'

export const useSearchFormStore = defineStore('searchForm', {
  state: () => ({
    form: {
      keyword: '',
      tags: {
        artist: [],
        character: [],
        copyright: [],
        meta: [],
        general: [],
      },
      sortBy: 'title',
      sortDirection: 'asc',
    } as SearchForm,
  }),

  actions: {
    resetForm() {
      this.form = {
        keyword: '',
        tags: {
          artist: [],
          character: [],
          copyright: [],
          meta: [],
          general: [],
        },
        sortBy: 'title',
        sortDirection: 'asc',
      }
    },
  },
})
