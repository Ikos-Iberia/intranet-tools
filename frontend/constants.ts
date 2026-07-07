import { Tool, ToolTag } from './types';

export const TOOLS: Tool[] = [
  // Public Utilities Apps (PDF Tools)
  {
    id: 'd1',
    nameKey: 'tool.split.name',
    descKey: 'tool.split.desc',
    icon: 'content_cut',
    tag: ToolTag.PUBLIC,
    metadata: 'v2.1',
    actionTextKey: 'tool.action.launch',
    category: 'Public Utilities',
    iconBgColor: 'bg-red-50',
    iconColor: 'text-red-600'
  },
  {
    id: 'd2',
    nameKey: 'tool.merge.name',
    descKey: 'tool.merge.desc',
    icon: 'call_merge',
    tag: ToolTag.PUBLIC,
    metadata: 'v2.1',
    actionTextKey: 'tool.action.launch',
    category: 'Public Utilities',
    iconBgColor: 'bg-red-50',
    iconColor: 'text-red-600'
  },
  {
    id: 'd3',
    nameKey: 'tool.organize.name',
    descKey: 'tool.organize.desc',
    icon: 'format_list_bulleted',
    tag: ToolTag.PUBLIC,
    metadata: 'v2.1',
    actionTextKey: 'tool.action.launch',
    category: 'Public Utilities',
    iconBgColor: 'bg-red-50',
    iconColor: 'text-red-600'
  },

  // IT Tools
  {
    id: 'it1',
    nameKey: 'tool.glpi.name',
    descKey: 'tool.glpi.desc',
    icon: 'inventory_2',
    tag: ToolTag.INTERNAL,
    metadata: 'v2.1',
    actionTextKey: 'tool.action.launch',
    category: 'IT',
    iconBgColor: 'bg-blue-50',
    iconColor: 'text-primary'
  },
  {
    id: 'it2',
    nameKey: 'tool.delivery.name',
    descKey: 'tool.delivery.desc',
    icon: 'local_shipping',
    tag: ToolTag.INTERNAL,
    metadata: 'v2.1',
    actionTextKey: 'tool.action.launch',
    category: 'IT',
    iconBgColor: 'bg-blue-50',
    iconColor: 'text-primary'
  },
  {
    id: 'it3',
    nameKey: 'tool.return.name',
    descKey: 'tool.return.desc',
    icon: 'assignment_return',
    tag: ToolTag.INTERNAL,
    metadata: 'v2.1',
    actionTextKey: 'tool.action.launch',
    category: 'IT',
    iconBgColor: 'bg-blue-50',
    iconColor: 'text-primary'
  },
  {
    id: 'it4',
    nameKey: 'tool.userdoc.name',
    descKey: 'tool.userdoc.desc',
    icon: 'menu_book',
    tag: ToolTag.INTERNAL,
    metadata: 'v2.1',
    actionTextKey: 'tool.action.launch',
    category: 'IT',
    iconBgColor: 'bg-blue-50',
    iconColor: 'text-primary'
  },

  // HR Tools
  {
    id: 'hr1',
    nameKey: 'tool.payroll.name',
    descKey: 'tool.payroll.desc',
    icon: 'payments',
    tag: ToolTag.INTERNAL,
    metadata: 'v2.1',
    actionTextKey: 'tool.action.launch',
    category: 'HR',
    iconBgColor: 'bg-green-50',
    iconColor: 'text-green-600'
  }
];