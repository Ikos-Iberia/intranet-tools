import React from 'react';
import { Tool, ToolTag } from '../types';

interface ToolCardProps {
  tool: Tool;
  t: (key: string) => string;
}

const ToolCard: React.FC<ToolCardProps> = ({ tool, t }) => {
  const getTagStyle = (tag: ToolTag) => {
    switch (tag) {
      case ToolTag.RESTRICTED:
        return 'bg-accent/20 text-primary border border-accent/30';
      case ToolTag.ADMIN:
        return 'bg-accent text-primary font-black';
      case ToolTag.INTERNAL:
        return 'bg-accent text-primary font-black';
      case ToolTag.PUBLIC:
        return 'bg-slate-100 text-slate-500 border border-slate-200';
      default:
        return 'bg-slate-100 text-slate-600';
    }
  };

  const isRestrictedOrInternal = tool.tag === ToolTag.RESTRICTED || tool.tag === ToolTag.INTERNAL || tool.tag === ToolTag.ADMIN;

  return (
    <div className="bg-white border border-slate-200 rounded-2xl overflow-hidden hover:shadow-xl hover:shadow-slate-200/50 hover:-translate-y-1 transition-all flex flex-col group">
      <div className="p-6 flex-1">
        <div className="flex justify-between items-start mb-5">
          <div className={`w-12 h-12 rounded-xl ${tool.iconBgColor} flex items-center justify-center ${tool.iconColor} shadow-sm group-hover:scale-110 transition-transform`}>
            <span className="material-symbols-outlined text-3xl">{tool.icon}</span>
          </div>
          <span className={`text-[10px] font-bold px-2.5 py-1 rounded-full uppercase tracking-wider flex items-center gap-1.5 ${getTagStyle(tool.tag)}`}>
            {isRestrictedOrInternal && (
              <span className="material-symbols-outlined text-[12px] font-bold">
                {tool.tag === ToolTag.RESTRICTED ? 'security' : 'lock'}
              </span>
            )}
            {tool.tag}
          </span>
        </div>

        <h3 className="text-xl font-bold mb-2 text-slate-900 group-hover:text-primary transition-colors">{t(tool.nameKey)}</h3>
        <p className="text-slate-500 text-sm leading-relaxed mb-6 font-medium">
          {t(tool.descKey)}
        </p>
      </div>

      <div className="px-6 py-4 bg-slate-50/50 border-t border-slate-100 flex items-center justify-between">
        <div className="flex items-center gap-2">
          {tool.status === 'active' && <span className="w-2 h-2 rounded-full bg-green-500 animate-pulse"></span>}
          <span className={`text-xs font-bold ${tool.status === 'active' ? 'text-green-600' : 'text-slate-400'}`}>
            {tool.metadata}
          </span>
        </div>
        <button className="text-primary text-sm font-bold flex items-center gap-1.5 group/btn hover:underline transition-all">
          {t(tool.actionTextKey)} 
          <span className="material-symbols-outlined text-sm font-bold group-hover/btn:translate-x-1 transition-transform">arrow_forward</span>
        </button>
      </div>
    </div>
  );
};

export default ToolCard;