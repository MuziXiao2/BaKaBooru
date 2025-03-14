import api from '../index.ts'
import type { AxiosResponse } from 'axios'
import type { Atlas, AtlasRequestDTO } from '@/types/module/atlas'

export function getAtlases(sourceId: number): Promise<AxiosResponse<Atlas[]>> {
  return api.get('', {
    params: {
      type: 'atlas',
      source_id: sourceId,
    },
  })
}

export function addAtlas(atlas: AtlasRequestDTO) {
  return api.post('', atlas, {
    params: {
      type: 'atlas',
    },
  })
}
