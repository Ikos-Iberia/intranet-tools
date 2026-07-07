import React, { useState } from 'react';
import { ViewType } from '../App';
import { Language } from '../types';

interface HeaderProps {
  onSearch: (query: string) => void;
  toggleMobileMenu: () => void;
  currentView: ViewType;
  onNavigate: (view: ViewType) => void;
  lang: Language;
  onLangChange: (lang: Language) => void;
  onLogout: () => void;
  t: (key: string) => string;
}

const Header: React.FC<HeaderProps> = ({ onSearch, toggleMobileMenu, currentView, onNavigate, lang, onLangChange, onLogout, t }) => {
  const [showLangMenu, setShowLangMenu] = useState(false);
  const [showProfileMenu, setShowProfileMenu] = useState(false);

  const getViewName = () => {
    switch(currentView) {
      case 'it-tools': return t('nav.it');
      case 'hr-tools': return t('nav.hr');
      case 'helpdesk': return t('nav.helpdesk');
      default: return t('nav.public');
    }
  };

  const languages: { code: Language; name: string; flag: string }[] = [
    { code: 'en', name: 'English', flag: '🇺🇸' },
    { code: 'es', name: 'Español', flag: '🇪🇸' },
    { code: 'el', name: 'Ελληνικά', flag: '🇬🇷' },
  ];

  return (
    <header className="h-16 border-b border-slate-200 bg-white/80 backdrop-blur-md px-4 lg:px-8 flex items-center justify-between sticky top-0 z-40">
      <div className="flex items-center gap-3 lg:gap-8 flex-1 min-w-0">
        <button 
          onClick={toggleMobileMenu}
          className="lg:hidden p-2 text-slate-500 hover:bg-slate-100 rounded-xl transition-colors"
          aria-label="Toggle navigation menu"
        >
          <span className="material-symbols-outlined">menu</span>
        </button>

        <div className="hidden md:flex items-center gap-2">
          <button onClick={() => onNavigate('dashboard')} className="text-slate-400 hover:text-primary text-[11px] font-black uppercase tracking-widest transition-colors">SANIIKOS</button>
          <span className="text-slate-300">/</span>
          <span className="text-primary font-bold text-sm whitespace-nowrap">
            {getViewName()}
          </span>
        </div>
        
        {currentView !== 'helpdesk' && (
          <div className="relative w-full max-w-sm ml-2 sm:ml-4 group">
            <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-slate-400 text-xl transition-colors group-focus-within:text-primary">search</span>
            <input 
              type="text" 
              placeholder={t('header.search')}
              onChange={(e) => onSearch(e.target.value)}
              className="w-full bg-slate-50 border-transparent focus:bg-white focus:border-primary/20 focus:ring-4 focus:ring-primary/5 rounded-xl py-2 pl-10 pr-4 text-sm transition-all placeholder:text-slate-400"
            />
          </div>
        )}
      </div>

      <div className="flex items-center gap-2 sm:gap-6 ml-4">
        {/* Language Selector */}
        <div className="relative">
          <button 
            onClick={() => {
              setShowLangMenu(!showLangMenu);
              setShowProfileMenu(false);
            }}
            className="flex items-center gap-2 px-3 py-2 rounded-xl border border-slate-100 bg-slate-50 hover:bg-white hover:border-slate-200 transition-all group"
          >
            <span className="text-lg leading-none">{languages.find(l => l.code === lang)?.flag}</span>
            <span className="text-xs font-bold text-slate-700 uppercase tracking-wider hidden xs:block">{lang}</span>
            <span className="material-symbols-outlined text-slate-400 group-hover:text-primary text-lg">language</span>
          </button>

          {showLangMenu && (
            <>
              <div className="fixed inset-0 z-10" onClick={() => setShowLangMenu(false)} />
              <div className="absolute right-0 mt-2 w-48 bg-white border border-slate-200 rounded-2xl shadow-2xl z-20 overflow-hidden animate-in fade-in slide-in-from-top-2 duration-200">
                <div className="p-2 space-y-1">
                  {languages.map((l) => (
                    <button
                      key={l.code}
                      onClick={() => {
                        onLangChange(l.code);
                        setShowLangMenu(false);
                      }}
                      className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl text-sm transition-colors ${lang === l.code ? 'bg-primary text-white font-bold' : 'text-slate-600 hover:bg-slate-50'}`}
                    >
                      <span className="text-base">{l.flag}</span>
                      <span>{l.name}</span>
                      {lang === l.code && <span className="material-symbols-outlined ml-auto text-sm">check</span>}
                    </button>
                  ))}
                </div>
              </div>
            </>
          )}
        </div>
        
        <div className="hidden sm:block h-6 w-px bg-slate-200"></div>
        
        <div className="relative">
          <div 
            onClick={() => {
              setShowProfileMenu(!showProfileMenu);
              setShowLangMenu(false);
            }}
            className="flex items-center gap-3 cursor-pointer group"
          >
            <div className="text-right hidden sm:block">
              <p className="text-sm font-bold text-slate-900 group-hover:text-primary transition-colors">Alex Johnson</p>
              <p className="text-[10px] text-slate-400 font-bold uppercase tracking-tight mt-0.5">{t('header.role')}</p>
            </div>
            <div className="relative flex-shrink-0 transition-transform active:scale-95">
              <img 
                src="https://picsum.photos/seed/alex/100/100" 
                alt="Profile" 
                className={`w-9 h-9 sm:w-10 sm:h-10 rounded-xl border-2 ring-2 transition-all object-cover shadow-sm ${showProfileMenu ? 'border-primary ring-primary/20' : 'border-white ring-slate-100 group-hover:ring-primary/20'}`}
              />
              <div className="absolute -bottom-0.5 -right-0.5 w-3 h-3 bg-green-500 border-2 border-white rounded-full shadow-sm"></div>
            </div>
          </div>

          {showProfileMenu && (
            <>
              <div className="fixed inset-0 z-10" onClick={() => setShowProfileMenu(false)} />
              <div className="absolute right-0 mt-2 w-56 bg-white border border-slate-200 rounded-2xl shadow-2xl z-20 overflow-hidden animate-in fade-in slide-in-from-top-2 duration-200">
                <div className="p-4 border-b border-slate-100 bg-slate-50/50">
                  <p className="text-sm font-black text-slate-900">Alex Johnson</p>
                  <p className="text-[10px] text-slate-400 font-bold uppercase tracking-widest mt-0.5">{t('header.role')}</p>
                </div>
                <div className="p-2">
                  <button
                    onClick={onLogout}
                    className="w-full flex items-center gap-3 px-3 py-2.5 rounded-xl text-sm text-red-600 hover:bg-red-50 transition-colors group/btn"
                  >
                    <span className="material-symbols-outlined text-xl transition-transform group-hover/btn:scale-110">logout</span>
                    <span className="font-bold">Sign Out</span>
                  </button>
                </div>
              </div>
            </>
          )}
        </div>
      </div>
    </header>
  );
};

export default Header;