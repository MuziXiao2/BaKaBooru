import { defineStore } from 'pinia'

interface SearchForm {
  keyword: string
  tag: string
  createdAt: [string, string] | []
  updatedAt: [string, string] | []
  sortBy: 'title' | 'createdAt' | 'updatedAt'
  sortDirection: 'asc' | 'desc'
}

export const useSearchFormStore = defineStore('searchForm', {
  state: () => ({
    form: {
      keyword: '',
      tag: '',
      createdAt: [] as [string, string] | [],
      updatedAt: [] as [string, string] | [],
      sortBy: 'title',
      sortDirection: 'asc',
    } as SearchForm,
  }),

  actions: {
    resetForm() {
      this.form = {
        keyword: '',
        tag: '',
        createdAt: [],
        updatedAt: [],
        sortBy: 'title',
        sortDirection: 'asc',
      }
    },
  },
})
