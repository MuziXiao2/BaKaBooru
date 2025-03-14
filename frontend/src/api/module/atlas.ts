import api from '../index.ts'
import type { AxiosResponse } from 'axios'
import type { Atlas, AtlasRequestDTO } from '@/types/module/atlas'

export function getAtlases(source_id: number): Promise<AxiosResponse<Atlas[]>> {
  return api.get(`/atlases/${source_id}`)
}

export function addAtlas(atlas: AtlasRequestDTO) {
  return api.post(`/atlases`, atlas)
}
