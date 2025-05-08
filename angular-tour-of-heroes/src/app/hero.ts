import { Superpower } from './superpower';

export interface Hero {
  id: number;
  name: string;
  image: string;
  superpowers: Superpower[];
}