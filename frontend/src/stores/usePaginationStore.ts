import { defineStore } from 'pinia'

export const usePaginationStore = defineStore('pagination', {
  state: () => ({
    page: 1,
    pageSize: 20,
    noMoreData: false,
  }),

  actions: {
    goToPage(targetPage: number) {
      if (targetPage < 1) return
      this.page = targetPage
    },

    setPageSize(size: number) {
      if (size < 1) return
      this.pageSize = size
      this.page = 1
      this.noMoreData = false
    },

    setNoMoreData(value: boolean) {
      this.noMoreData = value
    },
  },
})
