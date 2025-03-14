import api from '../index.ts'
import type { AxiosResponse } from 'axios'
import type { Group } from '@/types'

export function createGroup(name: string) {
  return api.post(
    '',
    { name: name },
    {
      params: { type: 'group' },
    },
  )
}

export function getGroups(): Promise<AxiosResponse<Group[]>> {
  return api.get('', {
    params: {
      type: 'group',
    },
  })
}
