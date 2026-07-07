export enum ToolTag {
  INTERNAL = 'INTERNAL',
  RESTRICTED = 'RESTRICTED',
  PUBLIC = 'PUBLIC UTILITY',
  ADMIN = 'ADMIN ONLY'
}

export type Language = 'en' | 'es' | 'el';

export interface Tool {
  id: string;
  nameKey: string;
  descKey: string;
  icon: string;
  tag: ToolTag;
  metadata: string;
  actionTextKey: string;
  category: 'Public Utilities' | 'IT' | 'HR';
  iconBgColor: string;
  iconColor: string;
  status?: string;
}

export type TabType = 'All Tools' | 'Internal' | 'Public Utilities';