import React from 'react';
import { TOOLS } from '../constants';
import ToolCard from './ToolCard';
import { ViewType } from '../App';
import { Language } from '../types';

interface DashboardProps {
  searchQuery: string;
  currentView: ViewType;
  onNavigate: (view: ViewType) => void;
  t: (key: string) => string;
  lang: Language;
}

const Dashboard: React.FC<DashboardProps> = ({ searchQuery, currentView, onNavigate, t, lang }) => {
  const filteredTools = TOOLS.filter(tool => {
    const matchesSearch = t(tool.nameKey).toLowerCase().includes(searchQuery.toLowerCase()) || 
                          t(tool.descKey).toLowerCase().includes(searchQuery.toLowerCase());
    
    // Strict category filtering
    let matchesView = false;
    if (currentView === 'dashboard' && tool.category === 'Public Utilities') matchesView = true;
    if (currentView === 'it-tools' && tool.category === 'IT') matchesView = true;
    if (currentView === 'hr-tools' && tool.category === 'HR') matchesView = true;
    
    return matchesSearch && matchesView;
  });

  const getPageTitle = () => {
    switch(currentView) {
      case 'dashboard': return t('dashboard.title.public');
      case 'it-tools': return t('dashboard.title.it');
      case 'hr-tools': return t('dashboard.title.hr');
      default: return 'Applications';
    }
  };

  const getPageSubtitle = () => {
    switch(currentView) {
      case 'dashboard': return t('dashboard.subtitle.public');
      case 'it-tools': return t('dashboard.subtitle.it');
      case 'hr-tools': return t('dashboard.subtitle.hr');
      default: return 'Select a category from the sidebar to view available tools.';
    }
  };

  return (
    <div className="flex-1 overflow-y-auto p-6 lg:p-12 animate-in fade-in duration-500">
      <div className="max-w-6xl mx-auto">
        <div className="mb-12">
          <div className="flex items-center gap-3 mb-3">
             <span className="material-symbols-outlined text-primary bg-primary/10 p-2 rounded-lg text-xl">
               {currentView === 'it-tools' ? 'terminal' : currentView === 'hr-tools' ? 'badge' : 'grid_view'}
             </span>
             <h2 className="text-4xl font-black tracking-tight text-slate-900">{getPageTitle()}</h2>
          </div>
          <p className="text-slate-500 text-lg font-medium max-w-2xl">
            {getPageSubtitle()}
          </p>
        </div>

        {/* Tools Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mb-12">
          {filteredTools.length > 0 ? (
            filteredTools.map((tool) => (
              <ToolCard key={tool.id} tool={tool} t={t} />
            ))
          ) : (
            <div className="col-span-full py-20 bg-white rounded-3xl border border-dashed border-slate-200 flex flex-col items-center justify-center text-center">
              <span className="material-symbols-outlined text-6xl text-slate-200 mb-4">search_off</span>
              <h3 className="text-xl font-bold text-slate-900">{t('dashboard.no_results')}</h3>
              <p className="text-slate-400 mt-1">{t('dashboard.no_results_desc')}</p>
            </div>
          )}

          <button 
            onClick={() => onNavigate('helpdesk')}
            className="border-2 border-dashed border-slate-200 rounded-3xl flex flex-col items-center justify-center p-8 text-center group cursor-pointer hover:border-primary hover:bg-primary/5 hover:shadow-xl hover:shadow-primary/5 transition-all outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2"
          >
            <div className="w-12 h-12 rounded-full bg-slate-50 flex items-center justify-center text-slate-400 group-hover:bg-white group-hover:text-primary mb-4 transition-all shadow-sm">
              <span className="material-symbols-outlined text-3xl font-light">add</span>
            </div>
            <h3 className="font-bold text-slate-900 text-base mb-1">{t('dashboard.request')}</h3>
            <p className="text-[10px] text-slate-400 font-bold uppercase tracking-widest leading-relaxed">
              {t('dashboard.missing')}
            </p>
          </button>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;